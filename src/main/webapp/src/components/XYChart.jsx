import React from "react";
import Chart from "chart.js";

export default function XYChart({ id, data }) {
  setTimeout(() => {
    var myChart = new Chart(id, {
      type: "scatter",
      data: {
        datasets: [
          {
            label: { id },
            data: data,
            borderWidth: 1,
            pointBackgroundColor: "red",
            showLine: true,
            lineTension: 0.2,
            backgroundColor: "lightcoral"
          }
        ]
      },
      options: {}
    });
  }, 1000);

  return (
    <div className="chart-container">
      <canvas id={id} />
    </div>
  );
}
