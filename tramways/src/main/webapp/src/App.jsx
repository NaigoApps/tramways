import React, {useCallback, useState} from 'react';
import Root from "./Root";
import AppContext from "./AppContext";

export default function App() {

    const [error, setError] = useState(null);
    const [message, setMessage] = useState(null);
    const [loading, setLoading] = useState(0);
    const [appBarTitle, setAppBarTitle] = useState("");

    const clearError = useCallback(() => setError(null), []);
    const notifyError = useCallback(err => setError(err), []);
    const clearMessage = useCallback(() => setMessage(null), []);
    const stopLoading = useCallback(() => setLoading(loading => loading - 1), []);
    const startLoading = useCallback(() => setLoading(loading => loading + 1), []);

    return (
        <AppContext.Provider value={
            {
                error,
                notifyError,
                clearError,
                message,
                clearMessage,
                loading,
                startLoading,
                stopLoading,
                appBarTitle,
                setAppBarTitle
            }
        }>
            <Root/>
        </AppContext.Provider>
    );
}
