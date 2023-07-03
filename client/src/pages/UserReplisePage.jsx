import { Box } from '@mui/material';
import { useSelector } from 'react-redux';
import { UserReplise } from 'src/components/Replise/UserReplise';

export const UserReplisePage = () => {
  const userReplise =
    useSelector((state) => state.userReplise.userReplise) || [];
  console.log(userReplise);
  const replise = userReplise;
  console.log(replise);
  return (
    replise && (
      <Box>
        <UserReplise replise={replise} />
      </Box>
    )
  );
};
