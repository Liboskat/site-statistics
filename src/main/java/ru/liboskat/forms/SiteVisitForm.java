package ru.liboskat.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SiteVisitForm {
    @NotNull
    private Long userId;

    @NotNull
    private Long pageId;
}
