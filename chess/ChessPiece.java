package chess;

public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return owner;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;

		//  THIS IS A START... More coding needed

		// Verify that the indexes (from and to) associated with the Move object are not out-of- bounds
		if((move.fromRow >= 0 && move.fromRow < 8) &&
				(move.fromColumn >= 0 && move.fromColumn < 8) &&
				(move.toRow >= 0 && move.toRow < 8) &&
				(move.toColumn >= 0 && move.toColumn < 8)){

			// Verify that this piece is located at [move.fromRow, move.fromColumn] on the board.
			if(this.type().equals(board [move.fromRow] [move.fromColumn].type())){

				// Verify that the board at location [move.toRow, move.toColumn] does not contain a piece
				// or a piece belonging to the same player.
				if(board [move.toRow][move.toColumn] == null){
					// Verify that the starting and ending locations are different.
					if ((move.fromRow != move.toRow) || (move.fromColumn != move.toColumn)){
						valid = true;
					}
				}
				else if(this.owner.compareTo(board [move.toRow][move.toColumn].player()) != 0){
					// Verify that the starting and ending locations are different.
					if ((move.fromRow != move.toRow) || (move.fromColumn != move.toColumn)){
						valid = true;
					}
				}
			}
		}
		return valid;
	}
}
