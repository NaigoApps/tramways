import React, { Fragment, useState } from 'react';
import OkCancelDialog from './OkCancelDialog';
import { Button } from '@material-ui/core';

export default function ConfirmButton({
  confirmMessage,
  onClick,
  ...others
}) {
  const [dialogVisible, setDialogVisible] = useState(false);

  return (
    <Fragment>
      <Button
        {...others}
        onClick={() => setDialogVisible(true)}
      />
      {confirmMessage && (
      <OkCancelDialog
        onOk={() => {
          setDialogVisible(false);
          onClick();
        }}
        onCancel={() => setDialogVisible(false)}
        visible={dialogVisible}
      >
        <p>{confirmMessage}</p>
      </OkCancelDialog>
      )}
    </Fragment>
  );
}
