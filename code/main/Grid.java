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

import java.util.ArrayList;

/**
 * This class creates a grid.
 *
 * @author DUCOBU Alexandre
 */
public class Grid {

	private int line, column, row;
	private ArrayList<Cell> grid;
	private ArrayList<Boolean> list;
	private Cell cell;
	public final String ANSI_RESET = "\u001B[0m", ANSI_BLACK = "\u001B[30m", ANSI_RED = "\u001B[31m",
			ANSI_GREEN = "\u001B[32m", ANSI_YELLOW = "\u001B[33m", ANSI_BLUE = "\u001B[34m", ANSI_PURPLE = "\u001B[35m",
			ANSI_CYAN = "\u001B[36m", ANSI_WHITE = "\u001B[37m";

	/**
	 * Constructor of a grid.
	 * It initializes the number of lines and columns of the grid as well as the grid.
	 *
	 * @param line
	 * 		The number of lines of the grid.
	 * @param column
	 * 		The number of columns of the grid.
	 */
	public Grid(int line, int column) {
		this.line = line;
		this.column = column;
		row = (line <= column) ? column : line; //row is chosen from the size of the grid.
		grid = new ArrayList<Cell>(line*column);
		initGrid();
	}

	/**
	 * This method initializes all the cells of the grid by creating 'Cell' objects.
	 * The neighbours of each cell is also initialized.
	 */
	public void initGrid() {
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				grid.add(new Cell(i, j, this));
			}
		}
		for (Cell cell : grid) {
			cell.setNeighbours();
		}
	}

	/**
	 * Displays the grid in the terminal with colours.
	 */
	public void showGrid() {
		System.out.print(String.format("\033[H\033[2J"));
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				if (this.getCell(i, j).getState()) {
					System.out.print(ANSI_RED + "   ⦿   " + ANSI_RESET);
					//System.out.print(ANSI_RED + "   x   " + ANSI_RESET);
				} else {
					System.out.print(ANSI_YELLOW + "   ⚀   " + ANSI_RESET);
					//System.out.print(ANSI_YELLOW + "   +   " + ANSI_RESET);
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}

	/**
	 * Gets the number of lines in the grid.
	 *
	 * @return The number of lines in the grid.
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Gets the number of columns in the grid.
	 *
	 * @return The number of columns in the grid.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Gets the Cell from the chosen cell of the grid.
	 *
	 * @param line
	 * 		The line of the chosen cell.
	 * @param column
	 * 		The column of the chosen cell.
	 * @return The Cell from the chosen cell of the grid.
	 */
	public Cell getCell(int line, int column) {
		return grid.get((line * row) + column);
	}

	/**
	 * Calculates and returns the next state of the grid.
	 *
	 * @return The next state of the grid.
	 */
	public ArrayList<Boolean> getNextStep() {
		list = new ArrayList<Boolean>(line*column);

		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				cell = this.getCell(i, j);
				list.add((cell.getNeighbourNumber() == 3) || (cell.getState() && cell.getNeighbourNumber() == 2));
			}
		}

		return list;
	}

	/**
	 * Updates the grid to the next state.
	 *
	 * @param list
	 * 		The next state of the grid.
	 */
	public void setNextStep(ArrayList<Boolean> list) {

		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				this.getCell(i, j).setState(list.get((i * row) + j));
			}
		}
	}
}
