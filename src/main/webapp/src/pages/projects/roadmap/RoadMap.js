export default class RoadMap {
  constructor(map, onChanged) {
    this.changeUuid = 0;
    this.onChanged = onChanged;
    this.map = map;
    this.elements = {};

    map.points.forEach(point => {
      this.elements[point.uuid] = point;

      point.sources.forEach(lane => {
        let targetLane = findOrCreateLane(this.elements, lane);
        targetLane.source = point;
      });

      point.destinations.forEach(lane => {
        let targetLane = findOrCreateLane(this.elements, lane);
        targetLane.destination = point;
      });

      Object.entries(point.constraints).forEach(([lane, links]) => {
        let sourceLane = findOrCreateLane(this.elements, lane);
        links.forEach(link => {
          let destinationLane = findOrCreateLane(
            this.elements,
            link.destination
          );
          destinationLane.source = point;
        });
        sourceLane.destination = point;
      });

      //TODO Traffic lights
    });

    this.sources.forEach(source =>
      this.initLane(this.elements[source.targetLane], source.kind)
    );
  }

  initLane(lane, kind) {
    if (lane) {
      lane.kind = kind;
      this.nextLanes(lane).forEach(l => this.initLane(l, kind));
    }
  }

  get sources() {
    return this.map.points
      .filter(p => p.sources.length === 0)
      .map(p => this.elements[p.uuid]);
  }

  get destinations() {
    return this.map.points
      .filter(p => p.destinations.length === 0)
      .map(p => this.elements[p.uuid]);
  }

  // get trafficLights() {
  //   return this.map.points
  //     .filter(p => p.type === "trafficLight")
  //     .map(p => this.elements[p.uuid]);
  // }

  get crossingPoints() {
    return this.map.points
      .filter(p => p.destinations.length > 0)
      .filter(p => p.sources.length > 0)
      .map(p => this.elements[p.uuid]);
  }

  get properties() {
    return this.map.properties;
  }

  addProperties(element, properties) {
    this.map.properties[element] = properties;
  }

  get(uuid) {
    return this.elements[uuid];
  }

  sendUpdates() {
    this.changeUuid++;
    const changeUuid = this.changeUuid;
    setTimeout(() => {
      if (this.changeUuid === changeUuid) {
        this.onChanged(this.map);
      }
    }, 500);
  }

  nextLanes(lane) {
    switch (lane.destination.type) {
      case "trafficLight":
        const result = [];
        Object.entries(lane.destination.constraints)
          .filter(([l, links]) => l === lane.uuid)
          .forEach(([l, links]) => {
            links.forEach(link => result.push(this.elements[link.destination]));
          });
        return result;
      case "destination":
      case "source":
      default:
        return [];
    }
  }
}

function findOrCreateLane(elements, lane) {
  if (!elements[lane]) {
    elements[lane] = { uuid: lane };
  }
  return elements[lane];
}
