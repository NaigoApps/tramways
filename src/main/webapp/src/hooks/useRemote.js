import Axios from "axios";
import { useCallback, useContext, useEffect, useState } from "react";
import AppContext from "../AppContext";

export const appUrl = "http://localhost:8080/tramways-0.0.1-SNAPSHOT/rest/";
export function url(res) {
  return appUrl + res;
}

export default function useRemote(resource, config) {
  const { notifyError, startLoading, stopLoading } = useContext(AppContext);

  const [data, setData] = useState(null);

  const isSilent = config && config.silent;

  const refresh = useCallback(async () => {
    try {
      startLoading();
      const response = await Axios.get(appUrl + resource);

      setData(response.data);
      stopLoading();
    } catch (err) {
      stopLoading();
      setData(null);
      if (!isSilent) {
        if (err.response && err.response.data) {
          notifyError(err.response.data);
        } else {
          notifyError("Errore sconosciuto");
        }
      }
    }
  }, [isSilent, notifyError, resource, startLoading, stopLoading]);

  useEffect(() => {
    if (resource) {
      refresh();
    }
  }, [refresh, resource]);

  return [data, refresh];
}
