/*
 * MegaMekLab - Copyright (C) 2008
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.Vehicle.Printing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.ArrayList;

import megamek.common.Engine;
import megamek.common.Tank;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.UnitUtil;

public class PrintVehicle implements Printable {

	protected Image awtImage = null;
	private Tank tank = null;
	private ArrayList<Tank> tankList;

	public PrintVehicle(ArrayList<Tank> list) {
		awtImage = ImageHelper.getRecordSheet(list.get(0), list.get(0).getOInternal(Tank.LOC_TURRET) > 0);
		tankList = list;

		/*
		 * if (awtImage != null) { System.out.println("Width: " +
		 * awtImage.getWidth(null)); System.out.println("Height: " +
		 * awtImage.getHeight(null)); }
		 */
	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex >= 1) {
			return Printable.NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) graphics;
		// f.setPaper(this.paper);
		printImage(g2d, awtImage, pageFormat);
		return Printable.PAGE_EXISTS;
	}

	public void printImage(Graphics2D g2d, Image image, PageFormat pageFormat) {
		if (g2d == null) {
			return;
		}

		System.gc();
		g2d.drawImage(image, 18, 18, 558, 738, Color.BLACK, null);

		printTankData(g2d);
		printArmor(g2d);
		printWeaponsNEquipment(g2d);

		// Armor Pips
		printFrontArmor(g2d);

		// Internal Pips
		printFrontStruct(g2d);

		g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());

	}

	private void printTankData(Graphics2D g2d) {
		Font font = UnitUtil.deriveFont(true, 10.0f);
		g2d.setFont(font);

		g2d.drawString(tank.getChassis().toUpperCase() + " " + tank.getModel().toUpperCase(), 49, 121);

		font = UnitUtil.deriveFont(8.0f);
		g2d.setFont(font);

		g2d.drawString(Integer.toString(tank.getWalkMP()), 79, 144);
		g2d.drawString(Integer.toString(tank.getRunMP()), 79, 155);

		g2d.drawString(tank.getMovementModeAsString(), 85, 166);

		String engineName = "Fusion Engine";

		switch (tank.getEngine().getEngineType()) {
		case Engine.COMBUSTION_ENGINE:
			engineName = "I.C.E.";
			break;
		case Engine.LIGHT_ENGINE:
			engineName = "Light Fusion Engine";
			break;
		case Engine.XL_ENGINE:
			engineName = "XL Fusion Engine";
			break;
		case Engine.XXL_ENGINE:
			engineName = "XXL Fusion Engine";
			break;
		case Engine.COMPACT_ENGINE:
			engineName = "Compact Fusion Engine";
			break;
		default:
			break;
		}

		g2d.drawString(engineName, 79, 177);

		int tonnage = (int) Math.ceil(tank.getWeight());

		if (tonnage % 5 != 0) {
			tonnage += 5 - (tonnage % 5);
		}

		g2d.drawString(Integer.toString(tonnage), 177, 134);

		String techBase = "Inner Sphere";
		if (tank.isClan()) {
			techBase = "Clan";
		}
		g2d.drawString(techBase, 177, 145);

		g2d.drawString(Integer.toString(tank.getYear()), 188, 155);

		// Cost/BV
		DecimalFormat myFormatter = new DecimalFormat("#,###");
		g2d.drawString(myFormatter.format(tank.calculateBattleValue(true, true)), 150, 358);

		myFormatter = new DecimalFormat("#,###.##");
		g2d.drawString(myFormatter.format(tank.getCost()) + " C-bills", 52, 358);

		font = new Font("Arial", Font.BOLD, 7);
		g2d.setFont(font);
		g2d.drawString("2009", 105f, 745.5f);

	}

	private void printArmor(Graphics2D g2d) {
		// Armor
		Font font = UnitUtil.deriveFont(true, 9.0f);
		g2d.setFont(font);
		g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_FRONT)) + ")", 460, 64);

		g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_RIGHT)) + ")", 547, 230);

		int pipPoint = 169;
		int armor = tank.getArmor(Tank.LOC_LEFT);
		if (armor / 100 > 0) {
			g2d.drawString(Integer.toString(armor / 100), 388, pipPoint);
			armor -= (armor / 100) * 100;
		}
		pipPoint += 5;

		if (armor / 10 > 0) {
			g2d.drawString(Integer.toString(armor / 10), 388, pipPoint);
			armor -= (armor / 10) * 10;
		}
		pipPoint += 5;

		g2d.drawString(Integer.toString(armor), 388, pipPoint);

		g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_REAR)) + ")", 460, 342);

		if (tank.getOInternal(Tank.LOC_TURRET) > 0) {
			g2d.drawString("(" + Integer.toString(tank.getArmor(Tank.LOC_TURRET)) + ")", 448, 186);
		}

	}

	private void printWeaponsNEquipment(Graphics2D g2d) {

		ImageHelper.printTankWeaponsNEquipment(tank, g2d);
	}

	public void print() {

		try {
			PrinterJob pj = PrinterJob.getPrinterJob();

			if (pj.printDialog()) {
				// Paper paper = new Paper();
				PageFormat pageFormat = new PageFormat();
				pageFormat = pj.getPageFormat(null);

				Paper p = pageFormat.getPaper();
				p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
				pageFormat.setPaper(p);

				pj.setPrintable(this, pageFormat);

				for (Tank currentTank : tankList) {

					tank = currentTank;
					awtImage = ImageHelper.getRecordSheet(tank, tank.getOInternal(Tank.LOC_TURRET) > 0);
					pj.setJobName(tank.getChassis() + " " + tank.getModel());

					try {
						pj.print();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					System.gc();
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void printFrontArmor(Graphics2D g2d) {
		float[] topColumn = { 499, 181 };
		float[] middleColumn = { 509, 253 };
		float[] bottomColumn = { 531, 270 };
		float[] footColumn = { 519, 296 };
		float[] pipShift = { 8, -2 };

		int totalArmor = tank.getArmor(Tank.LOC_FRONT);

		if (totalArmor < 1) {
			return;
		}

		int pips = Math.min(20, totalArmor);

		totalArmor -= pips;

		int maxPipsPerTopColumn = 2;
		if (pips < 17) {
			maxPipsPerTopColumn = 1;
		}

		for (int pos = 1; pos <= pips; pos++) {
			ImageHelper.drawArmorPip(g2d, topColumn[0], topColumn[1]);
			topColumn[0] += pipShift[0];
			topColumn[1] += pipShift[1];
			if (pos % maxPipsPerTopColumn == 0) {
				pipShift[0] *= -1;
				topColumn[0] += pipShift[0] + 1.8f;
				pipShift[1] *= -1;
				topColumn[1] += pipShift[1] + 7;
			}
		}

		if (totalArmor < 1) {
			return;
		}

		pips = Math.min(12, totalArmor);

		totalArmor -= pips;
		for (int pos = 1; pos <= pips; pos++) {
			ImageHelper.drawArmorPip(g2d, middleColumn[0], middleColumn[1]);
			middleColumn[0] += pipShift[0];
			middleColumn[1] += pipShift[1];
			if (pos % 4 == 0) {
				pipShift[0] *= -1;
				middleColumn[0] += pipShift[0] + 1.8f;
				pipShift[1] *= -1;
				middleColumn[1] += pipShift[1] + 7;
			}

		}

		if (totalArmor < 1) {
			return;
		}

		pips = Math.min(6, totalArmor);

		totalArmor -= pips;

		for (int pos = 1; pos <= pips; pos++) {
			ImageHelper.drawArmorPip(g2d, bottomColumn[0], bottomColumn[1]);
			bottomColumn[0] += pipShift[0];
			bottomColumn[1] += pipShift[1];
			if (pos % 2 == 0) {
				pipShift[0] *= -1;
				bottomColumn[0] += pipShift[0] + 1.8f;
				pipShift[1] *= -1;
				bottomColumn[1] += pipShift[1] + 7;
			}
		}

		pips = Math.min(4, totalArmor);

		for (int pos = 1; pos <= pips; pos++) {
			ImageHelper.drawArmorPip(g2d, footColumn[0], footColumn[1]);
			footColumn[0] += pipShift[0];
		}

	}

	private void printFrontStruct(Graphics2D g2d) {
		Dimension column = new Dimension(506, 413);
		Dimension pipShift = new Dimension(4, 4);

		int totalArmor = tank.getInternal(Tank.LOC_FRONT);

		int pips = Math.min(16, totalArmor);

		totalArmor -= pips;

		for (int pos = 1; pos <= pips; pos++) {
			ImageHelper.drawISPip(g2d, column.width, column.height);
			column.height += pipShift.height;
			pipShift.width *= -1;
			column.width -= pipShift.width;

			if (pos % 4 == 0) {
				column.width += 2;
			}

		}

		if (totalArmor > 0) {
			column.height += pipShift.height;
			ImageHelper.drawISPip(g2d, column.width, column.height);
		}
	}

}