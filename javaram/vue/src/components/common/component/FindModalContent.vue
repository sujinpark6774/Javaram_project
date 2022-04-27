<template>
    <div v-if="searchPW">
        <SearchPW/>
    </div>
    <div v-else-if="!searchPW">
        <SearchID/>
    </div>

<div v-if="foundID">
    <KnownUserID/>
</div>

<div v-if="foundPW">
    <KnownUserPW/>
</div>

</template>

<script>
import SearchID from "../../account/component/SearchID.vue";
import SearchPW from "../../account/component/SearchPW.vue";
import KnownUserID from "../../account/component/KnownUserID.vue"
import KnownUserPW from "../../account/component/KnownUserPW.vue";
export default {
    components : {
        SearchID,
        SearchPW,
        KnownUserID,
        KnownUserPW
    },
data(){
      return {
          searchPW : true,
          foundID : true,
          foundPW : true,
        };
    }, 
};
</script>
