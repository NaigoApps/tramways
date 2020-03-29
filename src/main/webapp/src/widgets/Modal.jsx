import React from "react";

export default function Modal({ visible, children, onClose }) {
  return (
    visible && (
      <div className="modal is-active">
        <div className="modal-background" onMouseUp={onClose} />
        <div className={"modal-content is-large"}>
          <div className="box">{children}</div>
        </div>
        <button
          type="button"
          onMouseUp={onClose}
          className="modal-close is-large"
          aria-label="close"
        />
      </div>
    )
  );
}
