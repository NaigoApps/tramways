import React from "react";
import SensorTrafficLight from './SensorTrafficLight';

export default function TrafficLightComponent({ lane, light }) {
  const componentMap = {
    sensor: SensorTrafficLight
  };

  const Component = componentMap[light.type];
  return <Component lane={lane} light={light} />;
}
