package it.javavault.springMVC.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UsersMapper {

	/**
	 * Returns a list of users
	 * @return List<Map<String , Object>>
	 */
	@Select("SELECT * FROM USERS")
	List<Map<String, Object>> getUserList();

	/**
	 * Returns an user detail from USERID
	 * @param userid
	 * @return Map<String , Object>
	 */
	@Select("SELECT * FROM USERS WHERE USERID = #{userid}")
	Map<String, Object> getUserById(@Param("userid") BigDecimal userid);
	
	/**
	 * Returns an user detail from USERID
	 * @param params - Map<String , Object> (USERID , USERNAME , NICKNAME)
	 * @return Map<String , Object>
	 */
	Map<String, Object> getUserDetailById(Map<String, Object> params);
	
	/**
	 * update user Details by USERID
	 * @param Map<String, Object>
	 * @return 1 if success
	 */
	int updateUserDetailsById(Map<String, Object> params);

	/**
	 * update user Email by USERID
	 * @param userid
	 * @return 1 if success
	 */
	@Update("UPDATE USERS SET EMAIL = #{email} WHERE USERID = #{userid}")
	int updateUserEmailById(@Param("userid") BigDecimal userid,@Param("email") String email);
	
	/**
	 * delete user by USERID
	 * @param userid
	 * @return 1 if success
	 */
	@Delete("DELETE FROM USERS WHERE USERID = #{userid}")
	int deleteUserById(@Param("userid") BigDecimal userid);
	
	
}
