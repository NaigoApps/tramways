import { Button, FormControl, TextField } from "@material-ui/core";
import React, { useContext, useState } from "react";
import AppContext from "../AppContext";
import useNetwork from "../hooks/useNetwork";
import useRemote from "../hooks/useRemote";

export default function ProfilePage({ navigate }) {
  const { notifyMessage } = useContext(AppContext);
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [user] = useRemote("auth/session-info");
  const { post } = useNetwork();

  async function changePassword() {
    if (newPassword === confirmPassword) {
      const result = await post("auth/reset-password", {
        username: user.username,
        password: oldPassword,
        newPassword
      });
      if (result != null) {
        notifyMessage("Password changed successfully");
      }
    }
  }

  return (
    <section className="section">
      <div className="container">
        <h1 className="title">Your profile</h1>
        <div className="field">
          <TextField
            placeholder="Username"
            value={user && user.username}
            onChange={evt => {}}
            disabled
          />
        </div>
        <div className="columns">
          <div className="column">
            <FormControl>
              <TextField
                label="Old password"
                onChange={evt => setOldPassword(evt.target.value)}
                value={oldPassword}
                password
              />
            </FormControl>
          </div>
          <div className="column">
            <FormControl>
              <TextField
                label="New password"
                onChange={evt => setNewPassword(evt.target.value)}
                value={newPassword}
                password
              />
            </FormControl>
          </div>
          <div className="column">
            <FormControl>
              <TextField
                label="Confirm new password"
                onChange={setConfirmPassword}
                value={confirmPassword}
                error={
                  newPassword !== confirmPassword && "Not matching passwords"
                }
                password
              />
            </FormControl>
          </div>
        </div>
        <div className="columns">
          <div className="column">
            <Button variant="outlined" color="primary" onClick={changePassword}>
              Change password
            </Button>
          </div>
        </div>
      </div>
    </section>
  );
}
