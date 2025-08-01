package com.easybytes.accounts.DTO;

import java.util.List;
import java.util.Map;

public record AccountsContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
