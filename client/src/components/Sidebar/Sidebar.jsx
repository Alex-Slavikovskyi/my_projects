import TwitterIcon from '@mui/icons-material/Twitter';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { mainSidebarElemets } from './sidebarElemets';
import Link from '@mui/material/Link';
import { Box, Hidden } from '@mui/material';
import { SidebarFooter } from './SidebarFooter/SidebarFooter';
import { SidebarDropdown } from './SidebarDropdown/SidebarDropdown';
import { BtnTweet } from './BtnTweet/BtnTweet';
import SmallBtnTweet from './SmallBtnTweet/SmallBtnTweet';
import TweetButton from 'src/UI/TweetButton';

export const Sidebar = () => {
  return (
    <Drawer
      variant="permanent"
      anchor="left"
      sx={{
        flexShrink: 0,
        marginRight: '12px',
        width: '100%',
        // width: {lg: '72px'},
        // '@media (max-width: 1199px)': {
        //   width: '72px'
        // },
        '& .MuiDrawer-paper': {
          position: 'relative',
          width: '100%',
          border: 'none',
          // '@media (max-width: 1199px)': {
          //   width: '72px'
          // },
          boxSizing: 'border-box',
          backgroundColor: 'rgb(21,32,43)',
        },
      }}
    >
      <Box
        sx={{
          height: '100vh',
        }}
      >
        <Link
          href="/"
          display="flex"
          justifyContent="center"
          alignItems="center"
          sx={{
            width: '50px',
            height: '50px',
            ml: '20px',
            mt: '2px',
            '&:hover': {
              backgroundColor: 'rgb(39,51,64)',
              borderRadius: '30px',
            },
          }}
        >
          <TwitterIcon
            sx={{
              fontSize: 34,
            }}
          />
        </Link>

        <List
          sx={{
            mx: '10px',
          }}
        >
          {mainSidebarElemets.map((navElement) => (
            <Link href={navElement.route} underline="none" key={navElement.id}>
              <ListItem
                key={navElement.id}
                disablePadding
                sx={{ color: '#FFF' }}
              >
                <ListItemButton
                  sx={{
                    '&:hover': {
                      backgroundColor: 'rgb(39,51,64)',
                      borderRadius: '30px',
                    },
                  }}
                >
                  <ListItemIcon sx={{ fontSize: 30 }}>
                    <navElement.icon sx={{ fontSize: 30, color: '#FFF' }} />
                  </ListItemIcon>
                  {/* <Hidden lgDown> */}
                  <ListItemText
                    primaryTypographyProps={{ fontSize: '18px' }}
                    sx={{
                      display: { lg: 'block', xs: 'none' },
                    }}
                    primary={navElement.label}
                  />
                  {/* </Hidden> */}
                </ListItemButton>
              </ListItem>
            </Link>
          ))}

          <SidebarDropdown />

          <Hidden lgDown>
            <BtnTweet />
          </Hidden>

          <Hidden lgUp>
            <SmallBtnTweet />
          </Hidden>
        </List>

        <SidebarFooter displayName="Алексей SlaAll00" username="slaall00" />
      </Box>
    </Drawer>
  );
};
