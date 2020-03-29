import { Button, FormControl, Typography } from "@material-ui/core";
import React, { useState } from "react";
import useStyles from "../utils/styles";

export default function InputTextFile({ label, formats, onChange, variant }) {
  const classes = useStyles();

  function extension(file) {
    return file.name.split(".")[1];
  }

  function file2Text(inputFile) {
    const reader = new FileReader();
    return new Promise((resolve, reject) => {
      reader.onerror = () => {
        reader.abort();
        reject(new DOMException(""));
      };

      reader.onload = () => {
        resolve(reader.result);
      };
      reader.readAsText(inputFile);
    });
  }

  const [loading, setLoading] = useState(false);
  const [title, setTitle] = useState(null);

  async function handleFile(file, handleContent) {
    if (!file) {
      return;
    }

    let content = null;
    try {
      setLoading(true);
      if (!formats || formats.includes(extension(file))) {
        content = await file2Text(file);
      }
      handleContent(content, extension(file), file.size);
      setLoading(false);
      setTitle(file.name);
    } catch (e) {
      handleContent(null, null, null);
      setLoading(false);
      setTitle(null);
    }
  }

  return (
    <FormControl className={classes.formControl}>
      <Button
        disabled={loading}
        variant={variant}
        color="primary"
        component="label"
      >
        {label}
        <input
          type="file"
          accept={
            formats ? formats.map(format => `.${format}`).join(",") : null
          }
          onChange={event =>
            handleFile(
              event.currentTarget.files[0],
              (fileContent, fileFormat, fileSize) => {
                onChange(fileContent);
              }
            )
          }
          hidden
        />
      </Button>
      <Typography>{title}</Typography>
    </FormControl>
  );
}
