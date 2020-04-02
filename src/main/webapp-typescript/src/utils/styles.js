import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles(theme => ({
  appBarTitle: {
    flexGrow: 1
  },
  fab: {
    position: "absolute",
    top: theme.spacing(2),
    right: theme.spacing(2)
  },
  relative: {
    position: "relative"
  },
  container: {
    position: "relative",
    marginTop: theme.spacing(2)
  },
  card: {
    padding: theme.spacing(1)
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120
  }
}));

export default useStyles;
