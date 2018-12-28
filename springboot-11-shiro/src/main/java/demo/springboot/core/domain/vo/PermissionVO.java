package demo.springboot.core.domain.vo;

/**
 * @author LILUO
 * @date 2018/6/29
 */
public class PermissionVO {

    /**权限ID**/
    private Long permissionId;

    /**权限名**/
    private String permissionName;

    /**权限编号**/
    private String permissionCode;

    /**权限类型**/
    private Integer permissionType;

    /**备注**/
    private String permissionMark;

    /**所属菜单id**/
    private Long menuId;

    /**删除标识**/
    private Integer deleted;

    @Override
    public String toString() {
        return "PermissionVO{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", permissionCode='" + permissionCode + '\'' +
                ", permissionType=" + permissionType +
                ", permissionMark='" + permissionMark + '\'' +
                ", menuId=" + menuId +
                ", deleted=" + deleted +
                '}';
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionMark() {
        return permissionMark;
    }

    public void setPermissionMark(String permissionMark) {
        this.permissionMark = permissionMark;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
