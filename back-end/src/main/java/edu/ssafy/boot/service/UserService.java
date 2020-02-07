package edu.ssafy.boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.dto.WordCloudVo;
import edu.ssafy.boot.repository.IUserDAO;

@Service("UserService")
public class UserService implements IUserService {

	@Autowired
	@Qualifier("UserDAOImpl")
	IUserDAO dao;

	@Override
	public UserVo login(UserVo user) {
		return dao.login(user);
	}

	@Override
	public boolean signup(UserVo user) {
		List<String> interestList = user.getInterestList();
		List<String> dislikeList = user.getDislikeList();

		String interest = "";
		String dislike = "";

		if (interestList != null) {
			for (String in : interestList) {
				interest += in + " ";
			}
		}
		if (dislikeList != null) {
			for (String dis : dislikeList) {
				dislike += dis + " ";
			}
		}

		user.setInterest(interest.trim());
		user.setDislike(dislike.trim());
		user.setInterestList(null);
		user.setDislikeList(null);

		return dao.signup(user);
	}

	@Override
	public boolean updateUserInfo(UserVo user) {

		List<String> interestList = user.getInterestList();
		List<String> dislikeList = user.getDislikeList();

		String interest = "";
		String dislike = "";

		if (interestList != null) {
			for (String in : interestList) {

				in = in.replace(" ", "");
				interest += in + " ";
			}
		}
		if (dislikeList != null) {
			for (String dis : dislikeList) {
				dis = dis.replace(" ", "");

				dislike += dis + " ";
			}
		}

		user.setInterest(interest.trim());
		user.setDislike(dislike.trim());

		return dao.updateUserInfo(user);
	}

	@Override
	public boolean deleteUserInfo(String user_id) {

		return dao.deleteUserInfo(user_id);
	}

	@Override
	public UserVo info(String user_id) {
		UserVo user = dao.info(user_id);

		List<String> interestList = new ArrayList<String>();
		List<String> dislikeList = new ArrayList<String>();

		if (user.getInterest() == null) {
			user.setInterestList(null);
		} else {
			String interest = user.getInterest();
			String[] inter = interest.split(" ");
			for (int i = 0; i < inter.length; i++) {
				interestList.add(inter[i].trim().toString());
			}
			user.setInterestList(interestList);
			user.setInterest(null);
		}

		if (user.getDislike() == null) {
			user.setDislikeList(null);
		} else {
			String dislike = user.getDislike();
			String[] dis = dislike.split(" ");
			for (int i = 0; i < dis.length; i++) {
				if (dis[i].length() != 0)
					dislikeList.add(dis[i].trim().toString());
			}
			user.setDislikeList(dislikeList);
			user.setDislike(null);

		}

		return user;
	}

	@Override
	public void updateUserStatus() {
		dao.updateUserStatus();
	}

	@Override
	public boolean emailDuplicateCheck(String email) {
		return dao.emailDuplicateCheck(email);
	}

	@Override
	public List<UserVo> searchByUserId(String keyword) {
		return dao.searchByUserId(keyword);
	}

	@Override
	public List<UserVo> searchByInterest(List<String> list) {
		return dao.searchByInterest(list);
	}

	@Override
	public List<UserVo> userList() {
		return dao.userList();
	}
	
	@Override
	public String usertoday() {
		return dao.usertoday();
	}

	@Override
	public List<WordCloudVo> wordList() {
		List<String> interestList = dao.interestFrequency();
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (String string : interestList) {
			String[] arr = string.split(" ");
			for (String interest : arr) {
				if (map.containsKey(interest)) {
					map.replace(interest, map.get(interest) + 1);
				} else {
					map.put(interest, 1);
				}
			}
		}

		List<WordCloudVo> wordList = new ArrayList<WordCloudVo>();

		Set<Entry<String, Integer>> entrySet = map.entrySet();

		for (Entry<String, Integer> entry : entrySet) {
			WordCloudVo word = new WordCloudVo(entry.getKey(), entry.getValue());
			wordList.add(word);
		}

		return wordList;
	}
	
	@Override
	public int totalcontents(int month) {
		return dao.totalcontents(month);
	}
}
