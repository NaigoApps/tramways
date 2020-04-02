import {
  Button,
  Card,
  CardActions,
  CardContent,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Fab,
  Grid,
  Typography
} from "@material-ui/core";
import AddIcon from "@material-ui/icons/Add";
import React, { useState } from "react";
import useNetwork from "../../hooks/useNetwork";
import useRemote from "../../hooks/useRemote";
import SelectEditor from "../../inputs/SelectEditor";
import useStyles from "../../utils/styles";
import Page from "../Page";
import roles from "./roles";

export default function UsersPage({ navigate }) {
  const { put, remove } = useNetwork();
  const classes = useStyles();

  const [users, refresh] = useRemote("users");
  const [selectedUser, setSelectedUser] = useState(null);
  const [userRoles, setUserRoles] = useState([]);

  function editUser(user) {
    setSelectedUser(user);
    setUserRoles(user.roles.map(role => roles.find(r => r.id === role)));
  }

  async function updateUser() {
    const result = await put("users/" + selectedUser.uuid, {
      roles: userRoles.map(r => r.id)
    });
    if (result !== null) {
      refresh();
      setSelectedUser(null);
      setUserRoles([]);
    }
  }

  async function deleteUser(user) {
    await remove("users/" + user.uuid);
    refresh();
  }

  if (!users) {
    return null;
  }

  return (
    <Page title="Users">
      <Fab
        color="primary"
        aria-label="add"
        className={classes.fab}
        onClick={() => navigate("./new")}
      >
        <AddIcon />
      </Fab>
      <Grid container spacing={1}>
        {users.map(user => (
          <Grid item xs={4}>
            <Card className={classes.card}>
              <CardContent>
                <Typography variant="h5">{user.username}</Typography>
                <Typography variant="h6">
                  {`Roles: ${user.roles.join(", ")}`}
                </Typography>
              </CardContent>
              <CardActions>
                <Button
                  variant="outlined"
                  color="primary"
                  onClick={() => editUser(user)}
                >
                  Edit
                </Button>
                <Button color="primary" onClick={() => deleteUser(user)}>
                  Delete
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>
      <Dialog onClose={() => setSelectedUser(null)} open={!!selectedUser}>
        <DialogTitle>{`Edit user ${
          selectedUser ? selectedUser.username : ""
        }`}</DialogTitle>
        <DialogContent>
          <form>
            <SelectEditor
              label="Roles"
              options={roles}
              id={o => o.id}
              text={o => o.label}
              value={userRoles}
              onChange={setUserRoles}
              multiSelect
            />
          </form>
        </DialogContent>
        <DialogActions>
          <Button variant="contained" color="primary" onClick={updateUser}>
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
    </Page>
  );
}
