import Container from "@material-ui/core/Container";
import React, { useContext, useEffect } from "react";
import AppContext from "../AppContext";
import useStyles from "../utils/styles";

export default function Page({ title, children }) {
  const classes = useStyles();

  const { setAppBarTitle } = useContext(AppContext);

  useEffect(() => {
    setAppBarTitle(title);
  }, [setAppBarTitle, title]);

  return (
    <Container className={classes.container}>
      {/* {title && <Typography variant="h3">{title}</Typography>} */}
      {children}
    </Container>
  );
}
