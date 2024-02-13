{
<#if update_comment.id??>"id": "${update_comment.id}",</#if>
<#if update_comment.post.id??>"postId": "${update_comment.post.id}",</#if>
<#if update_comment.name??>"name": "${update_comment.name}",</#if>
<#if update_comment.email??>"email": "${update_comment.email}",</#if>
<#if update_comment.body??>"body": "${update_comment.body}"</#if>
}