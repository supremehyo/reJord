package com.dev6.mapper

import com.dev6.domain.model.challenge.ChallengeInfoRes
import com.dev6.model.challenge.ChallengeInfoResDTO

internal fun ChallengeInfoResDTO.toDomain() = ChallengeInfoRes(
    badgeId = badgeId,
    challengeId = challengeId,
    contents = contents,
    footprintAmount = footprintAmount,
    imgBack = imgBack,
    imgFront = imgFront,
    textColor = textColor,
    title = title,
    badgeName = badgeName
)