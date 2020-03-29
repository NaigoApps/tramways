import { Chip, Grid, Slider } from "@material-ui/core";
import React, { Fragment } from "react";
import { pointIcon } from "../../../utils/IconUtils";

export default function CrossingPointConstraint({
  map,
  point,
  source,
  destinations,
  onConfigure
}) {
  return (
    <Fragment>
      <Grid container spacing={1}>
        <Grid item>
          <Chip
            icon={pointIcon(map.get(source))}
            variant="outlined"
            label={`${source} towards`}
          />
        </Grid>
      </Grid>
      <Grid container spacing={1}>
        <Grid item>
          {destinations.length <= 1 &&
            destinations.map(d => (
              <Chip
                key={d.uuid}
                icon={pointIcon(map.get(d.destination))}
                variant="outlined"
                label={d.destination}
                onClick={() => onConfigure(d.uuid)}
              />
            ))}
          {destinations.length > 1 &&
            destinations.map(d => (
              <Fragment>
                <Chip
                  icon={pointIcon(map.get(d.destination))}
                  variant="outlined"
                  label={d.destination}
                />
                <Slider
                  defaultValue={d.weight}
                  onChange={(evt, value) => {
                    d.weight = value;
                    map.sendUpdates();
                  }}
                  valueLabelDisplay="auto"
                  step={5}
                  marks
                  min={0}
                  max={100}
                />
              </Fragment>
            ))}
        </Grid>
      </Grid>
    </Fragment>
  );
}
