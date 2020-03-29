import { Dialog, DialogContent, IconButton, Toolbar } from "@material-ui/core";
import CloseIcon from "@material-ui/icons/Close";
import React from "react";
// import { Scatter } from "react-chartjs-2";

export default function AnalysisResultDialog({ onClose, name, result }) {
  return (
    <Dialog onClose={onClose} open={!!result} fullScreen>
      <Toolbar>
        <IconButton edge="start" onClick={onClose}>
          <CloseIcon />
        </IconButton>
      </Toolbar>
      {result && (
        <DialogContent>
          {/*<Scatter*/}
          {/*  data={{*/}
          {/*    datasets: [*/}
          {/*      {*/}
          {/*        label: name,*/}
          {/*        data: result.points,*/}
          {/*        borderWidth: 1,*/}
          {/*        pointRadius: 2,*/}
          {/*        showLine: true,*/}
          {/*        lineTension: 0.2,*/}
          {/*        backgroundColor: "lightcoral"*/}
          {/*      }*/}
          {/*    ]*/}
          {/*  }}*/}
          {/*  options={{*/}
          {/*    maintainAspectRatio: false,*/}
          {/*    scales: {*/}
          {/*      xAxes: [*/}
          {/*        {*/}
          {/*          scaleLabel: {*/}
          {/*            display: true,*/}
          {/*            labelString: result.xLabel*/}
          {/*          }*/}
          {/*        }*/}
          {/*      ],*/}
          {/*      yAxes: [*/}
          {/*        {*/}
          {/*          scaleLabel: {*/}
          {/*            display: true,*/}
          {/*            labelString: result.yLabel*/}
          {/*          }*/}
          {/*        }*/}
          {/*      ]*/}
          {/*    }*/}
          {/*  }}*/}
          {/*/>*/}
        </DialogContent>
      )}
    </Dialog>
  );
}
