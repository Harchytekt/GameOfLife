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
 * This class creates a cell.
 *
 * @author DUCOBU Alexandre
 */
public class Cell {

	private int line, column;
	private boolean state = false;
	private Grid grid;
	private int[] neighboursLocation;
	private ArrayList<Cell> neighbours = new ArrayList<Cell>(8);

	/**
	 * Constructor of a cell.
	 * It initializes its location in the grid, its state and its neighbours' list.
	 *
	 * @param line
	 * 		The line in which the cell is located.
	 * @param column
	 * 		The column in which the cell is located.
	 * @param grid
	 * 		The grid.
	 */
	public Cell(int line, int column, Grid grid) {
		this.line = line;
		this.column = column;
		this.grid = grid;
		initNeighbours();
	}

	/**
	 * Gets the line in which the cell is located.
	 *
	 * @return The line in which the cell is located.
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Gets the column in which the cell is located.
	 *
	 * @return The column in which the cell is located.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Gets the state of the cell.
	 *
	 * @return True if the cell is alive, false otherwise.
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * Sets the state of the cell.
	 *
	 * @param state
	 * 		The state of the cell : true or false.
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/*public void setNeighbours() {
		if (line == 0) {
			if (column == 0) {
				neighbours.add(grid.getCell(line, column+1));
				neighbours.add(grid.getCell(line+1, column));
				neighbours.add(grid.getCell(line+1, column+1));
			} else if (column == grid.getColumn()-1) {
				neighbours.add(grid.getCell(line, column-1));
				neighbours.add(grid.getCell(line+1, column-1));
				neighbours.add(grid.getCell(line+1, column));
			} else {
				neighbours.add(grid.getCell(line, column-1));
				neighbours.add(grid.getCell(line, column+1));
				neighbours.add(grid.getCell(line+1, column-1));
				neighbours.add(grid.getCell(line+1, column));
				neighbours.add(grid.getCell(line+1, column+1));
			}
		} else if (line == grid.getLine()-1) {
			if (column == 0) {
				neighbours.add(grid.getCell(line-1, column));
				neighbours.add(grid.getCell(line-1, column+1));
				neighbours.add(grid.getCell(line, column+1));
			} else if (column == grid.getColumn()-1) {
				neighbours.add(grid.getCell(line, column-1));
				neighbours.add(grid.getCell(line-1, column-1));
				neighbours.add(grid.getCell(line-1, column));
			} else {
				neighbours.add(grid.getCell(line, column-1));
				neighbours.add(grid.getCell(line, column+1));
				neighbours.add(grid.getCell(line-1, column-1));
				neighbours.add(grid.getCell(line-1, column));
				neighbours.add(grid.getCell(line-1, column+1));
			}
		} else {
			if (column == 0) {
				neighbours.add(grid.getCell(line-1, column));
				neighbours.add(grid.getCell(line-1, column+1));
				neighbours.add(grid.getCell(line, column+1));
				neighbours.add(grid.getCell(line+1, column+1));
				neighbours.add(grid.getCell(line+1, column));
			} else if (column == grid.getColumn()-1) {
				neighbours.add(grid.getCell(line-1, column));
				neighbours.add(grid.getCell(line-1, column-1));
				neighbours.add(grid.getCell(line, column-1));
				neighbours.add(grid.getCell(line+1, column));
				neighbours.add(grid.getCell(line+1, column-1));
			} else {
				neighbours.add(grid.getCell(line-1, column-1));
				neighbours.add(grid.getCell(line-1, column));
				neighbours.add(grid.getCell(line-1, column+1));
				neighbours.add(grid.getCell(line, column-1));
				neighbours.add(grid.getCell(line, column+1));
				neighbours.add(grid.getCell(line+1, column-1));
				neighbours.add(grid.getCell(line+1, column));
				neighbours.add(grid.getCell(line+1, column+1));
			}
		}
	}*/

	/**
	 * Updates the neighbours' list of the cell.
	 */
	public void setNeighbours() {
		for (int location : neighboursLocation) {
			switch (location) {
				case 0:
					neighbours.add(grid.getCell(line-1, column-1));
					break;
				case 1:
					neighbours.add(grid.getCell(line-1, column));
					break;
				case 2:
					neighbours.add(grid.getCell(line-1, column+1));
					break;
				case 3:
					neighbours.add(grid.getCell(line, column-1));
					break;
				case 4:
					neighbours.add(grid.getCell(line, column+1));
					break;
				case 5:
					neighbours.add(grid.getCell(line+1, column-1));
					break;
				case 6:
					neighbours.add(grid.getCell(line+1, column));
					break;
				case 7:
					neighbours.add(grid.getCell(line+1, column+1));
					break;
			}
		}
	}

	/**
	 * Sets the location of the neighbours of the cell from its location.
	 */
	public void initNeighbours() {
		if (line == 0) {
			if (column == 0) {
				neighboursLocation = new int[]{4, 6, 7};
			} else if (column == grid.getColumn()-1) {
				neighboursLocation = new int[]{3, 5, 6};
			} else {
				neighboursLocation = new int[]{3, 4, 5, 6, 7};
			}
		} else if (line == grid.getLine()-1) {
			if (column == 0) {
				neighboursLocation = new int[]{1, 2, 4};
			} else if (column == grid.getColumn()-1) {
				neighboursLocation = new int[]{0, 1, 3};
			} else {
				neighboursLocation = new int[]{0, 1, 2, 3, 4};
			}
		} else {
			if (column == 0) {
				neighboursLocation = new int[]{1, 2, 4, 6, 7};
			} else if (column == grid.getColumn()-1) {
				neighboursLocation = new int[]{0, 1, 3, 5, 6};
			} else {
				neighboursLocation = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
			}
		}
	}

	/**
	 * Returns the number of living neighbours.
	 *
	 * @return The number of living neighbours.
	 */
	public int getNeighbourNumber() {
		int number = 0;
		for (Cell neighbour : neighbours) {
			if (neighbour.getState()) { number++; }
		}
		return number;
	}
}
