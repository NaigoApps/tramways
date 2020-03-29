import { Avatar, Card, CardContent, CardHeader, Chip } from "@material-ui/core";
import ErrorIcon from "@material-ui/icons/Error";
import MeetingRoomIcon from "@material-ui/icons/MeetingRoom";
import React from "react";
import { pointIcon } from "../../../utils/IconUtils";
export default function DestinationPoint({ map, point }) {
  // function findSource(lane) {
  //   let currentPoint = getPoint(map, lane.source);
  //   while (currentPoint.type !== "source") {
  //     currentPoint = getPoint(map, getSourceLanes(currentPoint)[0].source);
  //   }
  // }

  return (
    <Card>
      <CardHeader
        avatar={
          <Avatar>
            <MeetingRoomIcon />
          </Avatar>
        }
        title="Network exit"
      />
      <CardContent>
        {point.sources
          .map(l => map.get(l))
          .map(lane => (
            <Chip
              key={lane.uuid}
              icon={pointIcon(lane) || <ErrorIcon />}
              label={lane.uuid}
              variant="outlined"
            />
          ))}
      </CardContent>
    </Card>
  );
}
