package gna;

import java.util.*;

import libpract.PriorityFunc;

public class Solver
{
	private Stack<Board> orderBoards;
	/**
	 * Finds a solution to the initial board.
	 *
	 * @param priority is either PriorityFunc.HAMMING or PriorityFunc.MANHATTAN
	 */
	public Solver(Board initial, PriorityFunc priority) {
		if (initial == null)
			throw new NullPointerException("[Solver] initial board is empty. ");

		BoardComparator boardComparator = new BoardComparator(priority);
		PriorityQueue<Board> frontier = new PriorityQueue<>(1000, boardComparator);
		HashSet<Board> explored = new HashSet<>();

		frontier.add(initial);

		initial.setSolution();
		Board solution = new Board(initial.getSolution());
		Board state = null;
		while(! frontier.isEmpty()){
			state = frontier.poll();
			explored.add(state);

			if(state.equals(solution)){
				break;
			}


			for(Board neighbor : state.neighbors()){
				if(!(frontier.contains(neighbor)) && !(explored.contains(neighbor))){
					frontier.add(neighbor);
				}
				else if(frontier.contains(neighbor)){
					frontier.remove(neighbor);
					neighbor.updateMovesMade(state.getMovesMade());
					frontier.add(neighbor);
				}
			}
		}

		orderBoards = new Stack<>();
		while(state != null && state.getPreviousState() != null){
			orderBoards.push(state);
			state = state.getPreviousState();
		}
		orderBoards.push(initial);


//		BoardComparator boardComparator = new BoardComparator(priority);
//		PriorityQueue<Board> priorityQueue = new PriorityQueue<>(1000, boardComparator);
//		priorityQueue.add(initial);
//
//		Board solutionBoard = new Board(initial.getSolution());
//
//		Board currentBoard = priorityQueue.poll();
//		while (currentBoard != null && (!(currentBoard.equals(solutionBoard)) && currentBoard.getPriority(priority) != 0)) {
//
//			priorityQueue.addAll(currentBoard.neighbors());
//			currentBoard = priorityQueue.poll();
//
//
//		}
//
//		orderBoards = new Stack<>();
//		while(currentBoard != null && currentBoard.getPreviousState() != null){
//			orderBoards.push(currentBoard);
//			currentBoard = currentBoard.getPreviousState();
//		}
//
//		orderBoards.push(initial);
	}


		/**
		 * Returns a List of board positions as the solution. It should contain the initial
		 * Board as well as the solution (if these are equal only one Board is returned).
		 */
		public List<Board> solution ()
		{
			List<Board> ordered = new ArrayList<>();

			while(orderBoards.size() > 0){
				ordered.add(orderBoards.pop());
			}
			return ordered;
		}


	}


