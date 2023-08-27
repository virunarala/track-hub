package dev.virunarala.trackhub.data.network.model

import com.squareup.moshi.Json
import dev.virunarala.trackhub.data.db.model.RepositoryEntity

data class Repository(

    @Json(name = "id")
    val id: Int,

    @Json(name = "node_id")
    val nodeId: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "full_name")
    val fullName: String,

    @Json(name = "license")
    val license: License?,

    @Json(name = "organization")
    val organization: Organization?,

    @Json(name = "forks")
    val forks: Int,

    @Json(name = "permissions")
    val permissions: Permissions?,

    @Json(name = "owner")
    val owner: Owner,

    @Json(name = "private")
    val private: Boolean,

    @Json(name = "html_url")
    val htmlUrl: String,

    @Json(name = "description")
    val description: String?,

    @Json(name = "fork")
    val fork: Boolean,

    @Json(name = "url")
    val url: String,

    @Json(name = "archive_url")
    val archiveUrl: String,

    @Json(name = "assignees_url")
    val assigneesUrl: String,

    @Json(name = "blobs_url")
    val blobsUrl: String,

    @Json(name = "branches_url")
    val branchesUrl: String,

    @Json(name = "collaborators_url")
    val collaboratorsUrl: String,

    @Json(name = "comments_url")
    val commentsUrl: String,

    @Json(name = "commits_url")
    val commitsUrl: String,

    @Json(name = "compare_url")
    val compareUrl: String,

    @Json(name = "contents_url")
    val contentsUrl: String,

    @Json(name = "contributors_url")
    val contributorsUrl: String,

    @Json(name = "deployments_url")
    val deploymentsUrl: String,

    @Json(name = "downloads_url")
    val downloadsUrl: String,

    @Json(name = "events_url")
    val eventsUrl: String,

    @Json(name = "forks_url")
    val forksUrl: String,

    @Json(name = "git_commits_url")
    val gitCommitsUrl: String,

    @Json(name = "git_refs_url")
    val gitRefsUrl: String,

    @Json(name = "git_tags_url")
    val gitTagsUrl: String,

    @Json(name = "git_url")
    val gitUrl: String,

    @Json(name = "issue_comment_url")
    val issueCommentUrl: String,

    @Json(name = "issue_events_url")
    val issueEventsUrl: String,

    @Json(name = "issues_url")
    val issuesUrl: String,

    @Json(name = "keys_url")
    val keysUrl: String,

    @Json(name = "labels_url")
    val labelsUrl: String,

    @Json(name = "languages_url")
    val languagesUrl: String,

    @Json(name = "merges_url")
    val mergesUrl: String,

    @Json(name = "milestones_url")
    val milestonesUrl: String,

    @Json(name = "notifications_url")
    val notificationsUrl: String,

    @Json(name = "pulls_url")
    val pullsUrl: String,

    @Json(name = "releases_url")
    val releasesUrl: String,

    @Json(name = "ssh_url")
    val sshUrl: String,

    @Json(name = "stargazers_url")
    val stargazersUrl: String,

    @Json(name = "statuses_url")
    val statusesUrl: String,

    @Json(name = "subscribers_url")
    val subscribersUrl: String,

    @Json(name = "subscription_url")
    val subscriptionUrl: String,

    @Json(name = "tags_url")
    val tagsUrl: String,

    @Json(name = "teams_url")
    val teamsUrl: String,

    @Json(name = "trees_url")
    val treesUrl: String,

    @Json(name = "clone_url")
    val cloneUrl: String,

    @Json(name = "mirror_url")
    val mirror_url: String?,

    @Json(name = "hooks_url")
    val hooksUrl: String,

    @Json(name = "svn_url")
    val svnUrl: String,

    @Json(name = "homepage")
    val homepage: String?,

    @Json(name = "forks_count")
    val forksCount: Int,

    @Json(name = "stargazers_count")
    val stargazersCount: Int,

    @Json(name = "watchers_count")
    val watchersCount: Int,

    @Json(name = "size")
    val size: Int,

    @Json(name = "default_branch")
    val defaultBranch: String,

    @Json(name = "open_issues_count")
    val openIssuesCount: Int,

    @Json(name = "is_template")
    val isTemplate: Boolean? = false,

    @Json(name = "topics")
    val topics: List<String>?,

    @Json(name = "has_issues")
    val hasIssues: Boolean? = true,

    @Json(name = "has_projects")
    val hasProjects: Boolean? = true,

    @Json(name = "has_wiki")
    val hasWiki: Boolean? = true,

    @Json(name = "has_pages")
    val hasPages: Boolean,

    @Json(name = "has_downloads")
    val hasDownloads: Boolean? = true,

    @Json(name = "has_discussions")
    val hasDiscussions: Boolean? = false,

    @Json(name = "archived")
    val isArchived: Boolean? = false,

    @Json(name = "disabled")
    val isDisabled: Boolean,

    @Json(name = "visibility")
    val visibility: String,

    @Json(name = "pushed_at")
    val pushedAt: String?,

    @Json(name = "created_at")
    val createdAt: String?,

    @Json(name = "updated_at")
    val updatedAt: String?,

    @Json(name = "allow_rebase_merge")
    val allowRebaseMerge: Boolean? = true,

    @Json(name = "template_repository")
    val templateRepository: Repository?,

    @Json(name = "temp_clone_token")
    val tempCloneToken: String?,

    @Json(name = "allow_squash_merge")
    val allowSquashMerge: Boolean? = true,

    @Json(name = "allow_auto_merge")
    val allowAutoMerge: Boolean? = false,

    @Json(name = "delete_branch_on_merge")
    val deleteBranchOnMerge: Boolean? = false,

    @Json(name = "allow_update_branch")
    val allowUpdateBranch: Boolean? = false,

    @Json(name = "use_squash_pr_title_as_default")
    val useSquashPrTitleAsDefault: Boolean? = false,

    @Json(name = "squash_merge_commit_title")
    val squashMergeCommitTitle: Boolean?,

    @Json(name = "merge_commit_title")
    val mergeCommitTitle: Boolean?,

    @Json(name = "merge_commit_message")
    val mergeCommitMessage: Boolean?,

    @Json(name = "allow_merge_commit")
    val allowMergeCommit: Boolean? = false,

    @Json(name = "allow_forking")
    val allowForking: Boolean?,

    @Json(name = "web_commit_signoff_required")
    val webCommitSignoffRequired: Boolean? = false,

    @Json(name = "subscribers_count")
    val subscribersCount: Int?,

    @Json(name = "network_count")
    val networkCount: Int?,

    @Json(name = "open_issues")
    val openIssues: Int,

    @Json(name = "watchers")
    val watchers: Int,

    @Json(name = "master_branch")
    val masterBranch: String?,

    @Json(name = "starred_at")
    val starredAt: String?,

    @Json(name = "anonymous_access_enabled")
    val anonymousAccessEnabled: Boolean?,
) {

    fun toRepositoryEntity(): RepositoryEntity = RepositoryEntity(
        id,
        name,
        htmlUrl,
        description
    )
}
