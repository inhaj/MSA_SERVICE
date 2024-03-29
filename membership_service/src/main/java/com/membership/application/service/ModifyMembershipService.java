package com.membership.application.service;

import com.membership.adapter.out.persistence.MembershipJpaEntity;
import com.membership.adapter.out.persistence.MembershipMapper;
import com.membership.application.port.in.ModifyMembershipCommand;
import com.membership.application.port.in.ModifyMembershipUseCase;
import com.membership.application.port.out.ModifyMembershipPort;
import com.membership.domain.Membership;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import msa.hexagonal.common.common.UseCase;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;
    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );
        //entity -> Membership
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
