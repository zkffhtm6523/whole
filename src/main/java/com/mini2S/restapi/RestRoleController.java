package com.mini2S.restapi;

import com.mini2S.entity.Roles;
import com.mini2S.service.RolesService;
import com.mini2S.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"2. Roles"})
@RestController
@AllArgsConstructor
public class RestRoleController {
    private RolesService rolesService;

    private UsersService usersService;

    // 권한 정보
    @PostMapping("/roles/add")
    @ApiOperation(value = "권한 추가")
    public Roles addRoles(String roleName) {
        Roles roles;
        roles = getRoleName(roleName); // 입력하는 권한이 존재하는지 조회
        List<String> roleNames = rolesService.findAllRoleName();
        if(roles == null || !roleNames.contains(roleName)) { // 없으면 새로 등록
            roles.setRoleName(roleName);
            return rolesService.save(roles);
        }
        return roles; // 있으면 있는걸로 반환
    }

    // rolename 조회
    public Roles getRoleSeq(Long roleSeq) {
        return rolesService.findRoleNameByRoleSeq(roleSeq);
    }

    // roleseq 조회
    public Roles getRoleName(String roleName) {
        return rolesService.findRoleSeqByRoleName(roleName);
    }

}
