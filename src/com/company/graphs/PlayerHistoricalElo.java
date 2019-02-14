package com.company.graphs;

import com.company.model.Player;
import java.awt.Color;
import java.awt.BasicStroke;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

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

public class PlayerHistoricalElo extends ApplicationFrame {
	private PlayerHistoricalElo(Player player) {
		super("Rankings Data");
		JFreeChart xyLineChart = ChartFactory.createXYLineChart(
				player.getName() + "'s Historical Elo",
				"Played Games","Elo",
				createDataset(player),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel(xyLineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
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
		double minElo = Collections.min(player.getHistoricalElo());
		double maxElo = Collections.max(player.getHistoricalElo());
		range.setRange(minElo - 20, maxElo + 20);

		NumberAxis domain = (NumberAxis) plot.getDomainAxis();
		domain.setRange(0, player.getHistoricalElo().size() + 1);
	}

	private static XYDataset createDataset(Player player) {
		final XYSeries playerXY = new XYSeries(player.getName());

		for(int i = 0; i < player.getHistoricalElo().size(); i++) {
			playerXY.add(i + 1, player.getHistoricalElo().get(i));
		}

		final XYSeriesCollection dataset = new XYSeriesCollection( );
		dataset.addSeries(playerXY);
		return dataset;
	}

	public static void graph(Player player) {
		PlayerHistoricalElo chart = new PlayerHistoricalElo(player);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}

	public static void saveGraph(Player player) {
		JFreeChart xyLineChart = ChartFactory.createXYLineChart(
				player.getName() + "'s Historical Elo",
				"Played Games","Elo",
				createDataset(player),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel(xyLineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		final XYPlot plot = xyLineChart.getXYPlot();

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
		double minElo = Collections.min(player.getHistoricalElo());
		double maxElo = Collections.max(player.getHistoricalElo());
		range.setRange(minElo - 20, maxElo + 20);

		NumberAxis domain = (NumberAxis) plot.getDomainAxis();
		domain.setRange(0, player.getHistoricalElo().size() + 1);

		File XYChart = new File("./playergraphs/" + player.getName().replace(".", "").replace(" ", "") + ".jpeg");

		try {
			ChartUtilities.saveChartAsJPEG( XYChart, xyLineChart, 800, 600);
		} catch(IOException io) {
			throw new RuntimeException();
		}
	}
}

