import { useCallback, useContext } from "react";
import Axios from "axios";
import AppContext from "../AppContext";

export const appUrl = "http://localhost:8080/tramways-0.0.1-SNAPSHOT/rest/";

export default function useNetwork(onFail) {
  const { notifyError, startLoading, stopLoading } = useContext(AppContext);

  const remoteCall = useCallback(
    async call => {
      try {
        startLoading();
        const response = await call();
        stopLoading();
        return response.data;
      } catch (err) {
        stopLoading();
        if (err.response && err.response.data) {
          onFail ? onFail(err.response.data) : notifyError(err.response.data);
        } else {
          onFail
            ? onFail("Errore sul server")
            : notifyError("Errore sul server");
        }
      }
      return null;
    },
    [notifyError, onFail, startLoading, stopLoading]
  );

  const get = useCallback(res => remoteCall(() => Axios.get(appUrl + res)), [
    remoteCall
  ]);
  const put = useCallback(
    (res, data) => remoteCall(() => Axios.put(appUrl + res, data)),
    [remoteCall]
  );
  const post = useCallback(
    (res, data) => remoteCall(() => Axios.post(appUrl + res, data)),
    [remoteCall]
  );
  const remove = useCallback(
    res => remoteCall(() => Axios.delete(appUrl + res)),
    [remoteCall]
  );

  return {
    get,
    put,
    post,
    remove
  };
}
