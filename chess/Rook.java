package chess;

public class Rook extends ChessPiece {

	private boolean firstMove;
	private boolean canCastle;

	public Rook(Player player) {

		super(player);
		
	}

	public String type() {
		
		return "Rook";
		
	}
	
	// determines if the move is valid for a rook piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// carry over conditions checked for all pieces
		boolean valid = super.isValidMove(move, board);

		// To move in a straight line either row or column must be held constant
		if((move.fromColumn == move.toColumn) || (move.fromRow == move.toRow)){
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

//			//castle rules
//			if(firstMove){
//				if((move.fromColumn == 0 && move.toColumn == 5)
//						|| (move.fromColumn == 7 && move.toColumn == 3)){
//					valid = valid && occupySpace()
//					canCastle = true;
//				}
//				else{
//					canCastle = false;
//				}
//			}
//			else {
//				// no piece can obstruct the path to the destination coordinate
//				// invoke recursion to check all the in-between spaces and see
//				// if destination is clear or owned by another player (capture)
//				valid = valid && occupySpace(move, board);
//			}
		}
		else{
			valid = false;
		}

        return valid;
		
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	public boolean isCanCastle() {
		return canCastle;
	}

	public boolean isFirstMove(){
		return firstMove;
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
		}

		//else look if the piece tries to "jump" another that isn't itself
		// (for initial move condition)
		else if(board[current.fromRow][current.fromColumn] != null &&
				!board[current.fromRow][current.fromColumn].equals(this)){
			return false;
		}

		//else the piece is approaching from a clear path so continue the path by finding
		//out the approach direction and increment the row or column accordingly
		else

			//if a vertical move
			if(current.fromColumn == current.toColumn){

				//if the difference between start and finish rows is
				//positive the piece is moving UP
				if(current.toRow - current.fromRow > 0){
					current.fromRow++;
				}

				//else it is moving DOWN
				else {
					current.fromRow--;
				}
			}

			//else horizontal move
			else{

				//if the difference between a start and finish
				//column is positive piece is moving RIGHT
				if(current.toColumn - current.fromColumn > 0){
					current.fromColumn++;
				}

				//else piece is moving LEFT
				else{
					current.fromColumn--;
				}
			}

			//recursion keeps the piece moving until it reaches
			//the destination spot
			return occupySpace(current, board);
	}
	
}
