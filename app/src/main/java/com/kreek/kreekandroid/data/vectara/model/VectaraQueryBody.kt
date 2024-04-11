package com.kreek.kreekandroid.data.vectara.model


import com.google.gson.annotations.SerializedName

data class VectaraQueryBody(
    @SerializedName("query") val query: List<Query?>?
) {
    data class Query(
        @SerializedName("query") val query: String?,
        @SerializedName("start") val start: Int?,
        @SerializedName("numResults") val numResults: Int?,
        @SerializedName("corpusKey") val corpusKey: List<CorpusKey?>?,
        @SerializedName("summary") val summary: List<Summary?>?
    ) {
        data class CorpusKey(
            @SerializedName("corpus_id") val corpusId: Int?
        )

        data class Summary(
            @SerializedName("max_summarized_results") val maxSummarizedResults: Int?,
            @SerializedName("response_lang") val responseLang: String?
        )
    }
}