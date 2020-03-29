import React from "react";
// import { Scatter } from "react-chartjs-2";
import { Card, CardContent, CardActions, Button } from "@material-ui/core";

export default function AnalysisComponent({
  analysis,
  onSelectAnalysis,
  onDeleteAnalysis
}) {
  const result = JSON.parse(analysis.analysisResult);

  return (
    <Card>
      <CardContent>
        {/*<Scatter*/}
        {/*  data={{*/}
        {/*    datasets: [*/}
        {/*      {*/}
        {/*        label: analysis.name,*/}
        {/*        data: result.points,*/}
        {/*        borderWidth: 1,*/}
        {/*        pointRadius: 0,*/}
        {/*        showLine: true,*/}
        {/*        lineTension: 0.2,*/}
        {/*        backgroundColor: "lightcoral"*/}
        {/*      }*/}
        {/*    ]*/}
        {/*  }}*/}
        {/*/>*/}
      </CardContent>
      <CardActions>
        <Button
          variant="contained"
          color="primary"
          onClick={() => onSelectAnalysis(result)}
        >
          Open
        </Button>
        <Button onClick={onDeleteAnalysis}>Delete</Button>
      </CardActions>
    </Card>
  );
}
