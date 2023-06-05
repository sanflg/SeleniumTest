package org.seleniumtest.api.pokeapi.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.seleniumtest.api.AnyFieldModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "is_main_series",
        "generation",
        "names",
        "effect_entries",
        "effect_changes",
        "flavor_text_entries",
        "pokemon"
})
@Data
public class Ability extends AnyFieldModel {
    @JsonProperty("id")
    public long id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("is_main_series")
    public boolean isMainSeries;
    @JsonProperty("generation")
    public Generation generation;
    @JsonProperty("names")
    public List<Name> names;
    @JsonProperty("effect_entries")
    public List<EffectEntry> effectEntries;
    @JsonProperty("effect_changes")
    public List<EffectChange> effectChanges;
    @JsonProperty("flavor_text_entries")
    public List<FlavorTextEntry> flavorTextEntries;
    @JsonProperty("pokemon")
    public List<Pokemon> pokemon;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "version_group",
            "effect_entries"
    })
    @Data
    public class EffectChange extends AnyFieldModel {
        @JsonProperty("version_group")
        public VersionGroup versionGroup;
        @JsonProperty("effect_entries")
        public List<EffectEntry__1> effectEntries;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "effect",
            "short_effect",
            "language"
    })
    @Data
    public class EffectEntry extends AnyFieldModel {
        @JsonProperty("effect")
        public String effect;
        @JsonProperty("short_effect")
        public String shortEffect;
        @JsonProperty("language")
        public Language__1 language;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "effect",
            "language"
    })
    @Data
    public class EffectEntry__1 extends AnyFieldModel {
        @JsonProperty("effect")
        public String effect;
        @JsonProperty("language")
        public Language__2 language;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "ability"
    })
    @Data
    public class Example extends AnyFieldModel {
        @JsonProperty("ability")
        public Ability ability;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "flavor_text",
            "language",
            "version_group"
    })
    @Data
    public class FlavorTextEntry extends AnyFieldModel {
        @JsonProperty("flavor_text")
        public String flavorText;
        @JsonProperty("language")
        public Language__3 language;
        @JsonProperty("version_group")
        public VersionGroup__1 versionGroup;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class Generation extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class Language extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class Language__1 extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class Language__2 extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class Language__3 extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "language"
    })
    @Data
    public class Name extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("language")
        public Language language;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "is_hidden",
            "slot",
            "pokemon"
    })
    @Data
    public class Pokemon extends AnyFieldModel {
        @JsonProperty("is_hidden")
        public boolean isHidden;
        @JsonProperty("slot")
        public long slot;
        @JsonProperty("pokemon")
        public Pokemon__1 pokemon;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class Pokemon__1 extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class VersionGroup extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "url"
    })
    @Data
    public class VersionGroup__1 extends AnyFieldModel {
        @JsonProperty("name")
        public String name;
        @JsonProperty("url")
        public String url;
    }
}