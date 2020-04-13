import {
  Button,
  Card,
  CardContent,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Fab,
  Grid,
  TextField,
  Typography,
  FormControl
} from "@material-ui/core";
import AddIcon from "@material-ui/icons/Add";
import CheckIcon from "@material-ui/icons/Check";
import React, { useState } from "react";
import useNetwork from "../../hooks/useNetwork";
import SelectEditor from "../../inputs/SelectEditor";
import useStyles from "../../utils/useStyles";
import Page from "../Page";
import PropertyInput from "../properties/PropertyInput";

const types = [
  {
    id: "INTEGER",
    label: "Integer"
  },
  {
    id: "DECIMAL",
    label: "Decimal"
  },
  {
    id: "STRING",
    label: "String"
  },
  {
    id: "DISTRIBUTION",
    label: "Distribution"
  }
];

export default function NewConfigurationPage({ navigate }) {
  const { post } = useNetwork();
  const classes = useStyles();

  const [addingProperty, setAddingProperty] = useState(false);

  const [name, setName] = useState("");
  const [properties, setProperties] = useState([]);

  const [propertyName, setPropertyName] = useState("");
  const [propertyType, setPropertyType] = useState(null);
  const [propertyValue, setPropertyValue] = useState(null);

  async function createConfiguration() {
    const result = await post("configurations", { name, properties });
    if (result) {
      navigate("./../" + result);
    }
  }

  function addProperty() {
    setProperties(
      properties.concat([
        { name: propertyName, type: propertyType.id, value: propertyValue }
      ])
    );
    setPropertyName("");
    setPropertyType(null);
    setPropertyValue(null);
    setAddingProperty(false);
  }

  return (
    <Page title="New configuration">
      <TextField
        variant="outlined"
        className={classes.formControl}
        label="Configuration name"
        value={name}
        onChange={evt => setName(evt.target.value)}
      />
      <Typography variant="h5">Properties</Typography>
      <Grid container spacing={1} className={classes.container}>
        <Fab
          variant="extended"
          color="primary"
          aria-label="add"
          className={classes.fab}
          onClick={() => setAddingProperty(true)}
        >
          <AddIcon />
          Add property
        </Fab>
        {properties.map(property => (
          <Grid item key={property.name} xs={4}>
            <Card className={classes.card}>
              <CardContent>
                <p>{property.name}</p>
                <p>{property.type}</p>
                <p>{property.value}</p>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
      <FormControl className={classes.formControl}>
        <Button
          variant="contained"
          color="primary"
          startIcon={<CheckIcon />}
          onClick={createConfiguration}
        >
          Create Configuration
        </Button>
      </FormControl>
      <Dialog open={addingProperty} onClose={() => setAddingProperty(false)}>
        <DialogTitle>New property</DialogTitle>
        <DialogContent>
          <form></form>
          <div>
            <TextField
              className={classes.formControl}
              variant="outlined"
              label="Property name"
              value={propertyName}
              onChange={evt => setPropertyName(evt.target.value)}
            />
          </div>
          <div>
            <SelectEditor
              label="Type"
              className={classes.formControl}
              options={types}
              id={o => o.id}
              text={o => o.label}
              value={propertyType}
              onChange={opt => {
                setPropertyType(opt);
                setPropertyValue(null);
              }}
            />
          </div>
          {propertyType && (
            <PropertyInput
              type={propertyType && propertyType.id}
              value={propertyValue}
              onChange={setPropertyValue}
            />
          )}
        </DialogContent>
        <DialogActions>
          <Button
            variant="contained"
            color="primary"
            startIcon={<CheckIcon />}
            onClick={addProperty}
          >
            Add property
          </Button>
        </DialogActions>
      </Dialog>
    </Page>
  );
}
