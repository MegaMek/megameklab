// Returns a static list of unit weight classes
import type { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  const weightClasses: string[] = ["Light", "Medium", "Heavy", "Assault"];
  res.status(200).json(weightClasses);
}
