package member;


// Memer 테이블의 컬럼
public class Member {

    private String name;
    private String userId;
    private String userPass;
    private String userRole;

    @Override
    public String toString() {
        return "Member{" + "name='" + name + '\'' + ", userId='" + userId + '\'' + ", userPass='" + userPass + '\'' + ", userRole='" + userRole + '\'' + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserRole() {
        return userRole;
    }
}
