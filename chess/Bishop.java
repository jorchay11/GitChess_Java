package chess;

public class Bishop extends ChessPiece {

	public Bishop(Player player) {
		super(player);
	}

	public String type() {
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// carry over conditions checked for all pieces
		boolean valid = super.isValidMove(move, board);

		// To move in a diagonal line the difference in the rows == difference in the columns
		if(Math.abs(move.toRow - move.fromRow) == Math.abs(move.toColumn - move.fromColumn)) {
			// create a copy move to send to recursive function
			Move ref = new Move();
			ref.fromColumn = move.fromColumn;
			ref.toColumn = move.toColumn;
			ref.fromRow = move.fromRow;
			ref.toRow = move.toRow;

			// no piece can obstruct the path to the destination coordinate
			// invoke recursion to check all the in-between spaces and see
			// if destination is clear or owned by another player (capture)
			valid = valid && occupySpace(ref, board);
		}
		else {
			valid = false;
		}

		return valid;
        // More code is needed
		
	}

	boolean occupySpace(Move current, IChessPiece[][] board){

		//see if the piece has reached the final destination of the move and return true
		// as long as the space isn't occupied by the same player (capture)
		if(current.fromColumn == current.toColumn && current.fromRow == current.toRow){
			if(board [current.toRow][current.toColumn] == null){
				return true;
			}
			else if(super.player().compareTo(board [current.toRow][current.toColumn].player()) != 0){
				return true;
			}
			else{
				return false;
			}
		}

		//else look if the piece tries to "jump" another that isn't itself
		// (for initial move condition)
		else if(board[current.fromRow][current.fromColumn] != null &&
				!board[current.fromRow][current.fromColumn].equals(this)){
			return false;
		}

		//else the piece is approaching from a clear path so continue the path by finding
		//out the approach direction and increment the row or column accordingly
		else{
			//4 diag moves, check for South
			if(current.toRow > current.fromRow){
				// East
				if(current.toColumn > current.fromColumn){
					current.fromRow++;
					current.fromColumn++;
				}
				// West
				else{
					current.fromRow++;
					current.fromColumn--;
				}
			}
			// North
			else{
				// East
				if(current.toColumn > current.fromColumn){
					current.fromRow--;
					current.fromColumn++;
				}
				// West
				else{
					current.fromRow--;
					current.fromColumn--;
				}
			}
		}



		//recursion keeps the piece moving until it reaches
		//the destination spot
		return occupySpace(current, board);
	}
}
