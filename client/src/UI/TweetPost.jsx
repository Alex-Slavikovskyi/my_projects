import { Box, Avatar, Typography, CardMedia } from '@mui/material';
import React from 'react';
import UserNames from './UserNames';
import PostIconList from 'src/components/Post/PostIconGroup/PostIconList';
import TranslatedText from './TranslatedText/TranslatedText';

function TweetPost({
  displayName,
  username,
  verified,
  text,
  image,
  logoUrl,
  showIconList,
  likes,
  reply,
  retweet,
}) {
  return (
    <Box
      sx={{
        borderBottom: '1px solid rgb(56, 68, 77)',
        width: '100%',
      }}
      padding={2}
      display="flex"
    >
      <Box padding={2}>
        <Avatar src={logoUrl} />
      </Box>

      <Box
        padding={1}
        sx={{
          width: '100%',
        }}
      >
        <UserNames
          fullName={username}
          verified={verified}
          userTag={displayName}
          postTime="10h"
        />

        <Typography variant="body" sx={{ fontSize: '15px' }}>
          {text}
        </Typography>
        <TranslatedText text={text} />
        {image ? (
          <CardMedia
            component="img"
            height="auto"
            image={image}
            alt="Paella dish"
            sx={{
              borderRadius: '20px',
              my: '20px',
            }}
          />
        ) : (
          false
        )}
        {showIconList ? (
          <PostIconList likes={likes} reply={reply} retweet={retweet} />
        ) : (
          false
        )}
      </Box>
    </Box>
  );
}

export default TweetPost;
