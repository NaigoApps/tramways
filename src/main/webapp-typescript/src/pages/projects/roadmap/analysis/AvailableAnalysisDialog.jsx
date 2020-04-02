import React from "react";
import {
  Dialog,
  DialogTitle,
  List,
  ListItem,
  ListItemAvatar,
  Avatar,
  ListItemText
} from "@material-ui/core";
import BarChartIcon from "@material-ui/icons/BarChart";
import useRemote from "../../../../hooks/useRemote";

export default function AvailableAnalysisDialog({
  visible,
  onClose,
  onChooseAnalysis
}) {
  const [types] = useRemote("analysis/types");

  if (!types) {
    return null;
  }

  return (
    <Dialog onClose={onClose} open={visible}>
      <DialogTitle>Choose analysis type</DialogTitle>
      <List>
        {types.map(type => (
          <ListItem
            key={type.id}
            button
            onClick={() => onChooseAnalysis(type.id)}
          >
            <ListItemAvatar>
              <Avatar>
                <BarChartIcon />
              </Avatar>
            </ListItemAvatar>
            <ListItemText primary={type.name} />
          </ListItem>
        ))}
      </List>
    </Dialog>
  );
}
