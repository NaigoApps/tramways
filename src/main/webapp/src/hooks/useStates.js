import { useState, useCallback } from "react";

export default function useStates(initialValue) {
  const [values, setValues] = useState(initialValue || {});

  const getValue = useCallback(
    name => {
      return values[name];
    },
    [values]
  );
  const setValue = useCallback((name, value) => {
    setValues(oldValues => {
      const result = { ...oldValues };
      result[name] = value;
      return result;
    });
  }, []);

  return [values, getValue, setValue];
}
