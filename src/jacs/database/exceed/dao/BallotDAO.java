package jacs.database.exceed.dao;

import java.util.List;

import jacs.database.exceed.model.Ballot;
import jacs.database.exceed.model.User;

public interface BallotDAO {
	public List<Ballot> getVote(User user);
	public String saveBallot(Ballot b);
}
