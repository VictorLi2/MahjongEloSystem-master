package com.company.graphs;

import com.company.model.GameManager;
import com.company.model.Player;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class EloDistribution extends ApplicationFrame {

	private EloDistribution() {
		super("Rankings Data");
		JFreeChart barChart = ChartFactory.createBarChart(
				"League Elo Distribution",
				"Elo",
				"Number of Players",
				createDataset(),
				PlotOrientation.VERTICAL,
				true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 500));
		final CategoryPlot plot = barChart.getCategoryPlot();
		setContentPane(chartPanel);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, Color.RED);


		plot.setBackgroundPaint(Color.DARK_GRAY);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
	}
	private CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Double> eloList = new LinkedList<Double>();

		for (Player player : GameManager.getInstance().getPlayers()) {
			eloList.add(player.getElo());
		}

		int lowestElo = (int) (eloList.get(0) - (eloList.get(0) % 100));
		int highestElo = (int) (eloList.get(eloList.size() - 1) + 100 - (eloList.get(eloList.size() - 1) % 100));

		for (int i = lowestElo; i < highestElo; i += 100) {
			int binSize = 0;
			for (int j = 0; j < eloList.size(); j++) {
				if (eloList.get(j) >= i) {
					if (eloList.get(j) < i + 100) {
						binSize++;
					} else {
						j = eloList.size();
					}
				}
			}
			dataset.addValue(binSize, "New System", i + "'s");
		}

		return dataset;
	}

	public static void graph() {
		EloDistribution graph = new EloDistribution();
		graph.pack();
		RefineryUtilities.positionFrameOnScreen(graph, 100.0, 0.0);
		graph.setVisible(true);
	}
}
