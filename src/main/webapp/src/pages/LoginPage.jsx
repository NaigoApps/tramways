import { Button, TextField, FormControl } from "@material-ui/core";
import React, { useContext, useState } from "react";
import useNetwork from "../hooks/useNetwork";
import SessionContext from "../SessionContext";
import Page from "./Page";
import useStyles from "../utils/styles";
import CheckIcon from "@material-ui/icons/Check";

export default function LoginPage({ navigate }) {
  const classes = useStyles();
  const { post } = useNetwork();
  const { refreshUser } = useContext(SessionContext);

  const [username, setUser] = useState("");
  const [password, setPassword] = useState("");

  async function login() {
    await post("auth/login", { username, password });
    refreshUser();
  }

  return (
    <Page title="Login">
      <form autoComplete="off" noValidate>
        <div>
          <TextField
            className={classes.formControl}
            variant="outlined"
            label="Username"
            value={username}
            onChange={evt => setUser(evt.target.value)}
          />
        </div>
        <div>
          <TextField
            variant="outlined"
            className={classes.formControl}
            value={password}
            label="Password"
            onChange={evt => setPassword(evt.target.value)}
            type="password"
          />
        </div>
        <div>
          <FormControl className={classes.formControl}>
            <Button
              startIcon={<CheckIcon />}
              variant="contained"
              color="primary"
              onClick={login}
            >
              Submit
            </Button>
          </FormControl>
        </div>
      </form>
    </Page>
  );
}
