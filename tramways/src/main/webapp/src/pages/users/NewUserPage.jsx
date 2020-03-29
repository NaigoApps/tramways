import { Button, TextField, FormControl } from "@material-ui/core";
import React, { useState } from "react";
import useNetwork from "../../hooks/useNetwork";
import useStyles from "../../utils/styles";
import Page from "../Page";
import CheckIcon from "@material-ui/icons/Check";

export default function NewUserPage({ navigate }) {
  const classes = useStyles();
  const { post } = useNetwork();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  // const [userRoles, setUserRoles] = useState([]);
  const [confirmPassword, setConfirmPassword] = useState("");

  async function createUser() {
    if (password === confirmPassword) {
      const result = await post("users/register", { username, password });
      if (result) {
        navigate("./../" + result);
      }
    }
  }

  return (
    <Page title="New user">
      <form autoComplete="off" noValidate>
        <div>
          <TextField
            variant="outlined"
            label="Username"
            value={username}
            onChange={evt => setUsername(evt.target.value)}
            className={classes.formControl}
          />
        </div>
        <div>
          <TextField
            variant="outlined"
            value={password}
            label="Password"
            onChange={evt => setPassword(evt.target.value)}
            className={classes.formControl}
            type="password"
          />
        </div>
        <div>
          <TextField
            variant="outlined"
            value={confirmPassword}
            label="Confirm password"
            onChange={evt => setConfirmPassword(evt.target.value)}
            error={confirmPassword !== password && "Not matching passwords"}
            helperText={
              confirmPassword !== password && "Not matching passwords"
            }
            className={classes.formControl}
            type="password"
          />
        </div>
        {/* <div>
          <FormControl className={classes.formControl}>
            <InputLabel>Roles</InputLabel>
            <SelectEditor
              options={roles}
              id={o => o.id}
              text={o => o.label}
              value={userRoles}
              onChange={setUserRoles}
              multiSelect
            />
          </FormControl>
        </div> */}
        <div>
          <FormControl className={classes.formControl}>
            <Button
              startIcon={<CheckIcon />}
              variant="contained"
              color="primary"
              onClick={createUser}
            >
              Confirm
            </Button>
          </FormControl>
        </div>
      </form>
    </Page>
  );
}
