import React from "react";

export default function({ label, children }) {
  return (
    <div className="field">
      {label && <label className="label">{label}</label>}
      {children}
    </div>
  );
}
