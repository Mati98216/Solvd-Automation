{
<#if comment.post.id??>"postId": ${comment.post.id},</#if>
"id":"type:Integer",
<#if comment.name??>"name": "${comment.name}",</#if>
<#if comment.email??>"email": "${comment.email}",</#if>
<#if comment.body??>"body": "${comment.body}"</#if>
}