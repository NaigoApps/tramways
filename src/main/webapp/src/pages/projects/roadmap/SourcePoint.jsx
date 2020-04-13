import { Avatar, Card, CardContent, CardHeader, Chip } from "@material-ui/core";
import MeetingRoomIcon from "@material-ui/icons/MeetingRoom";
import React from "react";
import { pointIcon } from "../../../utils/iconUtils";

export default function SourcePoint({ point }) {
  return (
    <Card>
      <CardHeader
        avatar={
          <Avatar>
            <MeetingRoomIcon />
          </Avatar>
        }
        title="Network entrance into"
      />
      <CardContent>
        <Chip
          icon={pointIcon(point)}
          label={point.destinations.join(", ")}
          variant="outlined"
        />
      </CardContent>
    </Card>
  );
}
