package chess;
//Pawn move checklist
//First move optional double --
//only eat diagonals --
//only forward moves - depend on black or white pieces --
//optional - en passant
//optional pawn promotion - probably handled in ChessModel

public class Pawn extends ChessPiece {

	private boolean firstMove;

	public Pawn(Player player) {
		super(player);
		firstMove = true;
	}

	public String type() {
		return "Pawn";
	}

	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// carry over conditions checked for all pieces
		boolean valid = super.isValidMove(move, board);

		//check correct move for Black and white pawns, White can only move UP
		//and Black can only move DOWN
		if((this.player().equals(Player.WHITE) && (move.toRow - move.fromRow) < 0)
				|| (this.player().equals(Player.BLACK) && (move.toRow - move.fromRow) > 0)){

				//double first move
				if(firstMove && (move.fromColumn == move.toColumn) && Math.abs(move.toRow - move.fromRow) == 2){

					// ensure nothing is in the path or in spot two ahead
					if(this.player().equals(Player.WHITE)){
						if(board[move.fromRow-1][move.fromColumn] == null){
							if(board[move.fromRow-2][move.fromColumn] == null){
								valid = valid;
							}
							else{
								valid = false;
							}
						}
						else{
							valid = false;
						}
					}
					// same thing but BLACK going down
					else{
						if(board[move.fromRow+1][move.fromColumn] == null){
							if(board[move.fromRow+2][move.fromColumn] == null){
								valid = valid;
							}
							else{
								valid = false;
							}
						}
						else{
							valid = false;
						}
					}
				}
				// check for eating only in diagonals so move one diagonally forward and
				// make sure a piece of the opposite color is being eaten
				else if((Math.abs(move.toRow - move.fromRow)) == 1 &&
						(Math.abs(move.toColumn - move.fromColumn) == 1)){
					if(board[move.toRow][move.toColumn] != null){
						if(this.player().equals(Player.WHITE)){
							if(move.fromRow - move.toRow == 1){
								valid = valid;
							}
							else{
								valid = false;
							}
						}
						else{
							if(move.toRow - move.fromRow == 1){
								valid = valid;
							}
							else{
								valid = false;
							}
						}
					}
					else{
						valid = false;
					}
				}
				// check for single move forward
				else if((move.fromColumn == move.toColumn)){
					if(this.player().equals(Player.WHITE)){
						if(move.fromRow - move.toRow == 1){
							valid = valid;
						}
						else{
							valid = false;
						}
					}
					else{
						if(move.toRow - move.fromRow == 1){
							valid = valid;
						}
						else{
							valid = false;
						}
					}
				}
				else{
					valid = false;
				}

		}
		else{
			valid = false;
		}

		//latch first move so that if the player elects not to double move,
		//they cannot after the fact
		if(firstMove && valid){
			firstMove = false;
		}
		return valid;

	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	public boolean isFirstMove(){
		return firstMove;
	}
}
