package main;

/*
	This program is a simple implementation of the Conway's Game of Life for the Terminal.
	Copyright (C) 2017 Ducobu Alexandre (Harchytekt)
	
	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * This is the main class of the project.
 *
 * @author DUCOBU Alexandre
 */
public class Main {

	/**
	 * Method that launches the program.
	 *
	 * @param args
	 * 		Optional argument for the program.
	 */
	public static void main(String[] args) {
		Grid grid = new Grid(9, 16);

		grid.getCell(2, 4).setState(true);
		grid.getCell(3, 3).setState(true);
		grid.getCell(3, 4).setState(true);
		grid.getCell(4, 2).setState(true);
		grid.getCell(4, 3).setState(true);
		grid.getCell(4, 4).setState(true);
		grid.getCell(5, 3).setState(true);
		grid.getCell(5, 4).setState(true);
		grid.getCell(6, 4).setState(true);

		grid.getCell(2, 11).setState(true);
		grid.getCell(3, 11).setState(true);
		grid.getCell(3, 12).setState(true);
		grid.getCell(4, 11).setState(true);
		grid.getCell(4, 12).setState(true);
		grid.getCell(4, 13).setState(true);
		grid.getCell(5, 11).setState(true);
		grid.getCell(5, 12).setState(true);
		grid.getCell(6, 11).setState(true);

		grid.showGrid();

		for (int i = 0; i < 16; i++) {
			System.out.print("\033[H\033[2J");
			grid.showGrid();
			grid.setNextStep(grid.getNextStep());
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
