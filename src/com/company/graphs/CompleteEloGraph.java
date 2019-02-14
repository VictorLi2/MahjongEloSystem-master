package com.company.graphs;

import com.company.model.GameManager;
import java.awt.Color;
import java.awt.BasicStroke;
import java.io.File;
import java.io.IOException;

import com.company.model.Player;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class CompleteEloGraph extends ApplicationFrame {
    private CompleteEloGraph() {
        super("Rankings Data");
        JFreeChart xyLineChart = ChartFactory.createXYLineChart(
                "League Elo",
                "Games Played","Elo",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1500, 1000));
        final XYPlot plot = xyLineChart.getXYPlot();
        setContentPane(chartPanel);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setBaseShapesVisible(false);
        plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(1000, 2100);

        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0, GameManager.getInstance().getCurrentGameNumber());

        File XYChart = new File("./leagueElo.jpeg");

        try {
            ChartUtilities.saveChartAsJPEG( XYChart, xyLineChart, 1500, 1000);
        } catch(IOException io) {
            throw new RuntimeException();
        }
    }

    private static XYDataset createDataset() {
        final XYSeriesCollection dataset = new XYSeriesCollection();

        for(Player player: GameManager.getInstance().getPlayers()) {
            if(player.gamesPlayed() >= 12) {
                final XYSeries playerXY = new XYSeries(player.getName());

                for(int i = 0; i < player.getHistoricalElo().size(); i++) {
                    playerXY.add(i + 1, player.getHistoricalElo().get(i));
                }

                dataset.addSeries(playerXY);
            }
        }

        return dataset;
    }

    public static void graph() {
        CompleteEloGraph chart = new CompleteEloGraph();
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
