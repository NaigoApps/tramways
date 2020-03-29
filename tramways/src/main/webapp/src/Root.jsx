import {
  AppBar,
  Button,
  Drawer,
  IconButton,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Toolbar,
  Typography
} from "@material-ui/core";
import GroupIcon from "@material-ui/icons/Group";
import MenuIcon from "@material-ui/icons/Menu";
import SettingsIcon from "@material-ui/icons/Settings";
import TramIcon from "@material-ui/icons/Tram";
import { navigate, Router } from "@reach/router";
import Cookies from "js-cookie";
import React, { useContext, useState } from "react";
import AppContext from "./AppContext";
import useRemote from "./hooks/useRemote";
import ConfigurationPage from "./pages/configurations/ConfigurationPage";
import ConfigurationsPage from "./pages/configurations/ConfigurationsPage";
import NewConfigurationPage from "./pages/configurations/NewConfigurationPage";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";
import NewProjectPage from "./pages/projects/NewProjectPage";
import ProjectPage from "./pages/projects/ProjectPage";
import ProjectsPage from "./pages/projects/ProjectsPage";
import RoadMapPage from "./pages/projects/RoadMapPage";
import NewUserPage from "./pages/users/NewUserPage";
import UserPage from "./pages/users/UserPage";
import UsersPage from "./pages/users/UsersPage";
import SessionContext from "./SessionContext";
import useStyles from "./utils/styles";
import Alert from "./widgets/Alert";
import Modal from "./widgets/Modal";
import AnalysisPage from "./pages/projects/roadmap/analysis/AnalysisPage";

export default function Root() {
  const {
    error,
    clearError,
    message,
    clearMessage,
    loading,
    appBarTitle
  } = useContext(AppContext);

  const classes = useStyles();

  const [loggedUser, refreshLoggedUser] = useRemote("auth/session-info", {
    silent: true
  });

  const [showDrawer, setShowDrawer] = useState(false);

  function logout() {
    Cookies.remove("authorization");
    refreshLoggedUser();
  }

  return (
    <SessionContext.Provider
      value={{
        user: loggedUser,
        refreshUser: refreshLoggedUser
      }}
    >
      <div className="App">
        <AppBar position="static">
          <Toolbar>
            <IconButton
              edge="start"
              color="inherit"
              onClick={() => setShowDrawer(true)}
            >
              <MenuIcon />
            </IconButton>
            <Typography className={classes.appBarTitle} variant="h6">
              {appBarTitle}
            </Typography>
            <div>
              {!loggedUser && (
                <Button color="inherit" onClick={() => navigate("/login")}>
                  Login
                </Button>
              )}
              {loggedUser && (
                <Button color="inherit" onClick={logout}>
                  Logout
                </Button>
              )}
            </div>
          </Toolbar>
        </AppBar>
        <Drawer open={showDrawer} onClose={() => setShowDrawer(false)}>
          <div
            onClick={() => setShowDrawer(false)}
            onKeyDown={() => setShowDrawer(false)}
          >
            {navContent(loggedUser)}
          </div>
        </Drawer>
        {!loggedUser && !loading && (
          <Router>
            <LoginPage default />
          </Router>
        )}
        {loggedUser && (
          <Router>
            <ProfilePage path="profile" />

            <ProjectsPage path="projects" default />
            <NewProjectPage path="projects/new" />
            <ProjectPage path="projects/:projectId" />
            <RoadMapPage path="projects/:projectId/:mapId" />
            <AnalysisPage path="projects/:projectId/:mapId/analysis" />

            <ConfigurationsPage path="configurations" />
            <NewConfigurationPage path="configurations/new" />
            <ConfigurationPage path="configurations/:confId" />

            <UsersPage path="users" />
            <NewUserPage path="users/new" />
            <UserPage path="users/:usrId" />
          </Router>
        )}

        <Alert visible={!!error} onClose={clearError}>
          <Typography color="error">{error}</Typography>
        </Alert>

        <Modal visible={!!message}>
          <p className="h5 has-text-success">{message}</p>
          <div className="columns">
            <div className="column">
              <Button text="Chiudi" onClick={clearMessage} />
            </div>
          </div>
        </Modal>
        {/* <header className="App-header">
        <XYChart id="test" data={[{ x: 0, y: 0 }, { x: 1, y: 1 }]} />
      </header> */}
      </div>
    </SessionContext.Provider>
  );
}

function navContent(loggedUser) {
  if (!loggedUser) {
    return null;
  }

  return (
    <List>
      {loggedUser.roles.includes("CLIENT") && (
        <ListItem button onClick={() => navigate("/projects")}>
          <ListItemIcon>
            <TramIcon />
          </ListItemIcon>
          <ListItemText primary="Projects" />
        </ListItem>
      )}
      {loggedUser.roles.includes("EXPERT") && (
        <ListItem button onClick={() => navigate("/configurations")}>
          <ListItemIcon>
            <SettingsIcon />
          </ListItemIcon>
          <ListItemText primary="Configurations" />
        </ListItem>
      )}
      {loggedUser.roles.includes("ADMIN") && (
        <ListItem button onClick={() => navigate("/users")}>
          <ListItemIcon>
            <GroupIcon />
          </ListItemIcon>
          <ListItemText primary="Users" />
        </ListItem>
      )}
    </List>
  );
}
